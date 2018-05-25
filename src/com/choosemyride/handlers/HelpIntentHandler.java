/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.choosemyride.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.choosemyride.Constants;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.choosemyride.Constants.BREAK_TIME_200MS;
import static com.amazon.ask.request.Predicates.intentName;

public class HelpIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String ssmlResp = "You can say " + BREAK_TIME_200MS +
                "Get cheapest " + BREAK_TIME_200MS +
                " or " + BREAK_TIME_200MS +
                "Get cheapest from Dublin California to Fremont California" + BREAK_TIME_200MS +
                " or " + BREAK_TIME_200MS +
                "Get estimates summary "+ BREAK_TIME_200MS +
                " or " + BREAK_TIME_200MS +
                "Get estimates summary from Dublin California to Fremont California" + BREAK_TIME_200MS +
                " Please specify the city and state "+ BREAK_TIME_200MS +
                "or"+ BREAK_TIME_200MS +
                " postal address";

        String cardText = " * Get cheapest \n" +
                " * Get cheapest from Dublin California to Fremont California \n" +
                " * Get estimates summary \n" +
                " * Get estimates summary from Dublin California to Fremont California \n\n" +
                " Please specify the city and state or postal address";

        return input.getResponseBuilder()
                .withSpeech(ssmlResp)
                .withStandardCard("Uber or Lyft", "You can start by saying \n\n" + cardText, Constants.UBER_LYFT_IMG)
                .withReprompt(ssmlResp)
                .build();
    }
}
