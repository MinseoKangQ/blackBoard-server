package dev.line4.blackBoard;

public class JsonString {

    // BlackBoard Controller
    public final static String BLACKBOARD_CONTENT = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"칠판 안녕\","
            + "    \"introduction\" : \"칠판 소개\","
            + "    \"userId\" : \"testttttttttttttttttttttt\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "    {"
            + "        \"num\" : 1,"
            + "        \"positionX\" : 0.1,"
            + "        \"positionY\" : 0.2,"
            + "        \"img\" : 3,"
            + "        \"width\" : 1.5,"
            + "        \"angle\" : 0.5,"
            + "        \"mirror\" : -1"
            + "    },"
            + "    {"
            + "        \"num\" : 2,"
            + "        \"positionX\" : 0.3,"
            + "        \"positionY\" : 0.4,"
            + "        \"img\" : 7,"
            + "        \"width\" : 1.2,"
            + "        \"angle\" : 0.7,"
            + "        \"mirror\" : 1"
            + "    }"
            + "]"
            + "}";

    public final static String BLACKBOARD_DUPLICATE = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : \"duplicateUserTest\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public final static String BLACKBOARD_DUPLICATE_BAD = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : \"thisIsDuplicateTestId\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public final static String BLACKBOARD_DUPLICATE_ID_1 = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : \"thisIsDuplicateTestId1\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public final static String BLACKBOARD_DUPLICATE_ID_2 = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : \"thisIsDuplicateTestId2\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public final static String READ_BLACKBOARD_AND_LETTER = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : "
            + "\"test1\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public final static String LETTER = "{"
            + "\"letter\" : {"
            + "    \"nickname\":\"닉네임~\","
            + "    \"content\":\"내용입니다\","
            + "    \"font\":\"Alien\","
            + "    \"align\":\"center\""
            + " },"
            + "\"stickers\":["
            + " ]"
            + "}";

    // Filtering Controller

    public final static String BLACKBOARD_SUCCESS_CONTENT = "{"
            + "\"blackboard\" : {"
            + "\"title\" : \"칠판 안녕\","
            + "\"introduction\" : \"칠판 소개\","
            + "\"userId\" : \"takaakkad\""
            + "}"
            + "}";

    public final static String BLACKBOARD_FAIL_CONTENT = "{"
            + "\"blackboard\" : {"
            + "\"title\" : \"칠판 시발\","
            + "\"introduction\" : \"칠판 소개\","
            + "\"userId\" : \"takaakkad\""
            + "}"
            + "}";

    public final static String LETTER_SUCCESS_CONTENT = "{"
            + "\"letter\" : {"
            + "\"nickname\":\"편지 작성자\","
            + "\"content\":\"이건 텍스트 에\""
            + "}"
            + "}";

    public final static String LETTER_FAIL_CONTENT = "{"
            + "\"letter\" : {"
            + "\"nickname\":\"편지 작성자\","
            + "\"content\":\"병신아\""
            + "}"
            + "}";

    // Letter Controller

    public final static String BLACKBOARD = "{"
            + "\"blackboard\" : {"
            + "    \"title\" : \"제목제목\","
            + "    \"introduction\" : \"소개소개\","
            + "    \"userId\" : \"this-is-test\","
            + "    \"openDate\" : \"2024-02-20T18:00:00\""
            + "},"
            + "\"stickers\" : ["
            + "]"
            + "}";

    public static final String LETTER_1 = "{"
            + "\"letter\" : {"
            + "    \"nickname\":\"test1\","
            + "    \"content\":\"내용입니다\","
            + "    \"font\":\"Alien\","
            + "    \"align\":\"center\""
            + " },"
            + "\"stickers\":["
            + " ]"
            + "}";

    public static final String LETTER_2 = "{"
            + "\"letter\" : {"
            + "    \"nickname\":\"test2\","
            + "    \"content\":\"내용입니다\","
            + "    \"font\":\"Alien\","
            + "    \"align\":\"center\""
            + " },"
            + "\"stickers\":["
            + " ]"
            + "}";

}
