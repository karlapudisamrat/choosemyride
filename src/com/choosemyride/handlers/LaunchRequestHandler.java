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
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.choosemyride.Constants.BREAK_TIME_200MS;
import static com.choosemyride.Constants.BREAK_TIME_300MS;
import static com.amazon.ask.request.Predicates.requestType;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechResp = "Welcome "+ BREAK_TIME_200MS +
                "I will help you choose between Uber and Lyft" + BREAK_TIME_300MS +
                "You can start by saying " + BREAK_TIME_200MS +
                "Get cheapest " + BREAK_TIME_200MS +
                " or " + BREAK_TIME_200MS +
                "Get estimates summary ";

        String cardText = " * Get cheapest \n" +
                            " * Get estimates summary";

        return input.getResponseBuilder()
                .withSpeech(speechResp)
                .withStandardCard("Uber or Lyft", "You can start by saying \n\n" + cardText, Constants.UBER_LYFT_IMG)
                .withReprompt(speechResp)
                .build();
    }
}