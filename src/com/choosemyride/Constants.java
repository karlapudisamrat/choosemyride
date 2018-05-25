package com.choosemyride;

import java.util.Collections;

import com.amazon.ask.model.interfaces.display.ImageInstance;
import com.amazon.ask.model.ui.Image;

public class Constants {

    public static final String BREAK_TIME_100MS = "<break time=\"100ms\"/>";

    public static final String BREAK_TIME_200MS = "<break time=\"200ms\"/>";

    public static final String BREAK_TIME_300MS = "<break time=\"300ms\"/>";

    public static final String BREAK_TIME_500MS = "<break time=\"500ms\"/>";

    public static final String UBER = "Uber";

    public static final String LYFT = "Lyft";

    public static final String SRC_DEST = "From \"%s\" to \"%s\" ";

    public static final String ANSWER_FORMAT = "Between uber and lyft, %s is the cheapest and approximately costs you %s %d$." +
            "\n\n Enjoy the ride! Good bye!";

    public static final String CHEAPEST_RIDE_FORMAT = "%s is your cheapest ride";

    public static String SPECIFY_PROPER_ADDRESS_SSML = "Please specify city and state "+ BREAK_TIME_200MS +"or"+
            BREAK_TIME_200MS +" postal address the next time";

    public static String SPECIFY_PROPER_ADDRESS = "Please specify city and state or postal address the next time";

    // Images
    public static final String UBER_IMG_URL = "https://cpk3rw.bn.files.1drv.com/y4mOOV7h92BrPzAodwoGZAl52_Ljvf82wOCVuV6RqwdVkAQy11Q85zHXTfQBkHrEGqWe78Zgv2L1NSxuq5WLpQLLZ8OREvLwYG6BeGVsrVX7q_3M-NI3Y356eoO1LGGqYqpLU1Zo5QONEioTBPQvHZC3Df0ufTuifXEhC_kYPHwokr3xRwEyv2uh_g4ggOUipqAcaIO4QW2tQIVRTBTeleH0A";

    public static final String LYFT_IMG_URL = "https://aficow.bn.files.1drv.com/y4mXjANq1Z-4s7PpAgOf-jYk-YfqIoavS9ITt6VjuMxntl-J408sWkqNnULvz5LAJF9R4g6XdAwmFtPAiIU3ZLACx_ErPGhdrDnJ3gagwcjBCZ-FK-BmukHLsrWAB92Llf5Gy92n_HLcyHDy8q70yZztlcUWMH_ZWPaXPEEJiaro7yH_E15B9cVi0QRXcVdyagMrCCZtfGnn0Jy2Ch0Wjio1g?width=630&height=630&cropmode=none";

    public static final String UBER_LYFT_IMG_URL = "https://zqicow.bn.files.1drv.com/y4mfpNlBg7ISO1aod8n54qfp7bieo2FY9JuHf7m39U-bCr39rxjdUMAF96uumrq8oI8dmDdU1VbcoaDm61joEgy3Rp_rkIYOgQWhGgy18sP1q4p1LuzfUUasCsE7gnO_xhXbdPf6zUkCIzSV-RyHTW1R_Bj6igYtS7Vt_7E_6Hkhgkm55ZC2GGM7jkECgNLiTBP3zmF7CtDN0X4E8TwYnvYkQ?width=800&height=800&cropmode=none";

    public static final String UBER_108_IMG_URL = "https://yqicow.bn.files.1drv.com/y4m8z_M__ih4-huyGAdDR13u1lFCDSnDDVNL9uQevNDkFrHNUxDkQO-Hwo2tknIZL12lVtcXy_-FJL7rgKqKCqEDOrQPyyB9HUvhOIG1WyK4jOGTI-3mxte6Jf67dVy3saITB8YXA72bS7gH1upuQ9csuh1hy72-bdsoqtmbSmiCoMBe7DQsgpct1fLop30vShdegxjzDW-noVq9PIIkamMEA?width=108&height=108&cropmode=none";

    public static final String LYFT_108_IMG_URL = "https://zpicow.bn.files.1drv.com/y4mYUAlthdhGKmlUox__Mwj06Sz4-irj4JL7_AWZsY1wfwH5Wtdalmx0GHMyYoDKxrJYkWsKzaNrKkOyHf2BJLmITJuEihvdfc01nSEooRoq2rqdIXVvNi5kx52TAP5YNTJZdHhmXgVip2ob3XQHkWDsW84TTuAS12gQrn9w_MsC2OcmOA0fUCGYIuWfEtw7EG4OwSh1f8dxKqhUY20SscG8g?width=108&height=108&cropmode=none";

    public static ImageInstance uberInstSmall = ImageInstance.builder().withUrl(Constants.UBER_108_IMG_URL).build();
    public static com.amazon.ask.model.interfaces.display.Image uberImgSmall = com.amazon.ask.model.interfaces.display.Image.builder().withSources(Collections.singletonList(uberInstSmall)).build();

    public static ImageInstance lyftInstSmall = ImageInstance.builder().withUrl(Constants.LYFT_108_IMG_URL).build();
    public static com.amazon.ask.model.interfaces.display.Image lyftImgSmall = com.amazon.ask.model.interfaces.display.Image.builder().withSources(Collections.singletonList(lyftInstSmall)).build();

    public static Image UBER_IMG = Image.builder().withLargeImageUrl(Constants.UBER_IMG_URL).build();

    public static Image LYFT_IMG = Image.builder().withLargeImageUrl(Constants.LYFT_IMG_URL).build();

    public static Image UBER_LYFT_IMG = Image.builder().withLargeImageUrl(Constants.UBER_LYFT_IMG_URL).build();
}