package dev.line4.blackBoard.filter;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.vane.badwordfiltering.BadWordFiltering;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FilterServiceTest {

    private static final BadWordFiltering badWordFiltering = new BadWordFiltering();

    @Test
    @DisplayName("비속어 포함 확인")
    void checkHasProfanity() {
        String badWord = "<div>우정하는 가현이에게</div>\\n\\n가현아 4년간 너무 고생많았어\\n드디어 졸업이네\\n\\n고등학교 때부터\\n대학교 때 지니에서까지\\n너는 항상 묵묵히 성실하게 할 일을 다 하는 멋진 사람이라고 생각하고 있었어\\n\\n갑자기 고딩때 교실에서 플래시몹 알려주던 가현이부터\\n피자집에서 남친 생겼다고 공개하던 가현이까지 쭉 떠오르네\\n\\n오래 봤지만 요새는 둘다 너무 바빠서 자주 보지는 못해 참 아쉽다\\n그래도 너를 응원하고 아끼는 마음만큼은 그대로야 너도 그러길\\n\\n지난 4년 같이 컴퓨터공학자로 성장할 수 있어서 좋았어\\n진짜 고생 많았다 가현아!!\\n\\n우리 앞으로는 너무 열심히 살지 않아도 되기를\\n적게 공부해서 지식은 많이 얻고 돈 많~~이 벌기를 항상 응원해\\n\\n연우가";
        System.out.println(badWordFiltering.change(badWord, new String[]{"_"}));
        assertTrue(badWordFiltering.check(badWord));

    }
}
