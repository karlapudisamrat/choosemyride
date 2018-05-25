/*
     Copyright 2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package com.choosemyride;

import com.amazon.ask.Skill;
import com.amazon.ask.Skills;
import com.amazon.ask.SkillStreamHandler;
import com.choosemyride.handlers.CancelandStopIntentHandler;
import com.choosemyride.handlers.CustomIntentHandler;
import com.choosemyride.handlers.HelpIntentHandler;
import com.choosemyride.handlers.SessionEndedRequestHandler;
import com.choosemyride.handlers.LaunchRequestHandler;

public class ChooseMyRideStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CustomIntentHandler(),
                        new CancelandStopIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public ChooseMyRideStreamHandler() {
        super(getSkill());
    }

}
