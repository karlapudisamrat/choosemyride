package com.choosemyride.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.choosemyride.Constants;
import com.choosemyride.CustomIntent;
import com.choosemyride.Orchestrator;
import com.choosemyride.data.model.ResponseEstimate;
import com.choosemyride.data.model.ResponseEstimateSummary;
import com.choosemyride.exceptions.LyftException;
import com.choosemyride.exceptions.MultipleLocationsFoundException;
import com.choosemyride.exceptions.NoLocationFoundException;
import com.choosemyride.exceptions.UberException;
import com.amazon.ask.model.DialogState;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Request;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;
import com.amazon.ask.model.interfaces.display.BackButtonBehavior;
import com.amazon.ask.model.interfaces.display.ListItem;
import com.amazon.ask.model.interfaces.display.ListTemplate1;
import com.amazon.ask.model.interfaces.display.PlainText;
import com.amazon.ask.model.interfaces.display.TextContent;
import static com.choosemyride.Constants.BREAK_TIME_100MS;
import static com.choosemyride.Constants.BREAK_TIME_200MS;
import static com.choosemyride.Constants.BREAK_TIME_500MS;
import static com.choosemyride.Constants.SRC_DEST;
import static com.choosemyride.Constants.lyftImgSmall;
import static com.choosemyride.Constants.uberImgSmall;
import static com.amazon.ask.request.Predicates.intentName;

/**
 * Handles all supported intents
 */
public class CustomIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName(CustomIntent.CHEAPEST.getName())) ||
                input.matches(intentName(CustomIntent.CHEAPEST_QUICK.getName())) ||
                input.matches(intentName(CustomIntent.SUMMARY.getName())) ||
                input.matches(intentName(CustomIntent.SUMMARY_QUICK.getName()));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        Request request = input.getRequestEnvelope().getRequest();
        IntentRequest intentRequest = (IntentRequest) request;
        Intent intent = intentRequest.getIntent();
        CustomIntent customIntent = CustomIntent.getIntent(intent.getName());

        if(customIntent == null) {
            return null;
        }

        DialogState dialogueState = intentRequest.getDialogState();

        if(dialogueState == null) {
            dialogueState = DialogState.COMPLETED;
        }

        if (dialogueState.equals(DialogState.STARTED) || dialogueState.equals(DialogState.IN_PROGRESS)) {

            return input.getResponseBuilder()
                    .withStandardCard("Uber or Lyft", "Collecting information ...", Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(false)
                    .addDelegateDirective(intent)
                    .build();

        }

        Slot srcSlot = intent.getSlots().get("src");
        Slot destSlot = intent.getSlots().get("dest");

        Optional<Response> validationResponse = validateSlots(srcSlot, destSlot, input);
        if(validationResponse != null) {
            return validationResponse;
        }

        String src = srcSlot.getValue();
        String dest = destSlot.getValue();

        validationResponse = validateAddresses(src, dest, input);
        if(validationResponse != null) {
            return validationResponse;
        }

        try {
            switch (customIntent) {
                case CHEAPEST:
                case CHEAPEST_QUICK:
                    return fulfillRequestCheap(src, dest, input);
                case SUMMARY:
                case SUMMARY_QUICK:
                    return fulfillRequestSummary(src, dest, input);
            }
        } catch (Exception ex) {
            return handleExceptions(ex, input);
        }

        return null;
    }

    private static Optional<Response> validateSlots(Slot src, Slot dest, HandlerInput input) {
        if(src == null || dest == null) {
            String response = "There is some problem collecting source and destination information. %s \n" +
                    "Please try again later. Good bye!";

            return input.getResponseBuilder()
                    .withSpeech(String.format(response, Constants.BREAK_TIME_200MS))
                    .withStandardCard("Apologies",
                            String.format(response, ""), Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(true)
                    .build();
        }

        return null;
    }

    private static  Optional<Response> validateAddresses(String srcAddress, String destAddress, HandlerInput input) {
        if(srcAddress == null || destAddress == null) {
            String response = "I am sorry. I could not understand source or destination address. %s \n" +
                    "Please specify city and state %s or %s postal address the next time. Good bye!";

            return input.getResponseBuilder()
                    .withSpeech(String.format(response, Constants.BREAK_TIME_300MS, Constants.BREAK_TIME_200MS, Constants.BREAK_TIME_200MS))
                    .withStandardCard("Apologies",
                            String.format(response, "", "", ""), Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(true)
                    .build();
        }

        return null;
    }

    private static  Optional<Response> fulfillRequestCheap(String src, String dest, HandlerInput input)
            throws NoLocationFoundException, MultipleLocationsFoundException{
        ResponseEstimate pair = Orchestrator.getCheapest(src, dest);

        String response = String.format(Constants.SRC_DEST, src, dest) + "\n\n" +
                String.format(Constants.ANSWER_FORMAT, pair.serviceName, "", pair.estimate);
        String responseSsml = String.format(Constants.SRC_DEST, src, dest) + Constants.BREAK_TIME_500MS +
                String.format(Constants.ANSWER_FORMAT, pair.serviceName, Constants.BREAK_TIME_200MS, pair.estimate);
        boolean isUber = pair.serviceName.startsWith(Constants.UBER);

        return input.getResponseBuilder()
                .withSpeech(responseSsml)
                .withStandardCard(String.format(Constants.CHEAPEST_RIDE_FORMAT, isUber? Constants.UBER: Constants.LYFT),
                        response, isUber? Constants.UBER_IMG: Constants.LYFT_IMG)
                .withShouldEndSession(true)
                .build();
    }

    private static  Optional<Response> fulfillRequestSummary(String src, String dest, HandlerInput input)
            throws NoLocationFoundException, MultipleLocationsFoundException{
        ResponseEstimateSummary summary = Orchestrator.getSummary(src, dest);

        ListTemplate1.Builder templateBuilder = ListTemplate1.builder()
                .withBackButton(BackButtonBehavior.HIDDEN)
                .withTitle("Uber and Lyft estimates summary");

        StringBuilder uberSummarySsml = new StringBuilder("Starting with Uber, " + BREAK_TIME_200MS);
        summary.getUberSummary().entrySet().forEach(entry -> {
            templateBuilder.addListItemsItem(ListItem.builder().withImage(uberImgSmall)
                    .withTextContent(TextContent.builder()
                            .withPrimaryText(PlainText.builder().withText(entry.getKey()).build())
                            .withTertiaryText(PlainText.builder().withText(entry.getValue() + "$").build())
                            .build())
                    .build());

            uberSummarySsml.append(entry.getKey()).append("  --  ").append(entry.getValue()).append("$ ").append(BREAK_TIME_100MS);
        });

        StringBuilder lyftSummarySsml = new StringBuilder(" Lets see Lyft now, " + BREAK_TIME_200MS);
        summary.getLyftSummary().entrySet().forEach(entry -> {
            templateBuilder.addListItemsItem(ListItem.builder().withImage(lyftImgSmall)
                    .withTextContent(TextContent.builder()
                            .withPrimaryText(PlainText.builder().withText(entry.getKey()).build())
                            .withTertiaryText(PlainText.builder().withText(entry.getValue() + "$").build())
                            .build())
                    .build());

            lyftSummarySsml.append(entry.getKey()).append("  --  ").append(entry.getValue()).append("$ ").append(BREAK_TIME_100MS);
        });

        String responseSsml = String.format(SRC_DEST, src, dest) + BREAK_TIME_500MS + uberSummarySsml.toString()
                + BREAK_TIME_500MS + lyftSummarySsml.toString() + BREAK_TIME_500MS + " Enjoy the ride! Good bye!";

        return input.getResponseBuilder()
                .withSpeech(responseSsml)
                .addRenderTemplateDirective(templateBuilder.build())
                .withShouldEndSession(true)
                .build();
    }

    private static Optional<Response> handleExceptions(Exception ex, HandlerInput input) {
        if(ex instanceof NoLocationFoundException) {
            String response = "Could not find location " + ex.getMessage() + ". %s";

            return input.getResponseBuilder()
                    .withSpeech(String.format(response, Constants.SPECIFY_PROPER_ADDRESS_SSML))
                    .withStandardCard("Apologies", String.format(response, Constants.SPECIFY_PROPER_ADDRESS), Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(true)
                    .build();
        } else if(ex instanceof MultipleLocationsFoundException) {
            String response = "Multiple locations found for " + ex.getMessage() + ". %s";

            return input.getResponseBuilder()
                    .withSpeech(String.format(response, Constants.SPECIFY_PROPER_ADDRESS_SSML))
                    .withStandardCard("Apologies", String.format(response, Constants.SPECIFY_PROPER_ADDRESS), Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(true)
                    .build();
        } else if(ex instanceof UberException) {
            return null;
        } else if(ex instanceof LyftException) {
            return null;
        } else {
            String response = "There is some problem with the request. Please try again or say HELP";
            return input.getResponseBuilder()
                    .withSpeech(response)
                    .withStandardCard("Apologies", response, Constants.UBER_LYFT_IMG)
                    .withShouldEndSession(false)
                    .build();
        }
    }
}
