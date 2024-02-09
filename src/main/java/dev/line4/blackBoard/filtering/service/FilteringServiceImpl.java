package dev.line4.blackBoard.filtering.service;

import com.vane.badwordfiltering.BadWordFiltering;
import dev.line4.blackBoard.filtering.dto.FilterBlackBoardDto;
import dev.line4.blackBoard.filtering.dto.FilterLetterDto;
import dev.line4.blackBoard.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilteringServiceImpl implements FilteringService {

    private static final BadWordFiltering badWordFiltering = new BadWordFiltering();

    static {
        badWordFiltering.remove("보지");
    }

    // 비속어 확인
    @Override
    public ResponseEntity<ApiResponse<?>> filterBlackBoard(FilterBlackBoardDto.Req req) {
        String title = req.getBlackboard().getTitle();
        String introduction = req.getBlackboard().getIntroduction();
        String userId = req.getBlackboard().getUserId();

        // 여기에 비속어 확인 로직 추가
        if (containsProfanity(title) || containsProfanity(introduction) || containsProfanity(userId)) {
            ApiResponse<String> errorResponse = ApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(),
                    "비속어가 포함되어 있습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            ApiResponse<String> successResponse = ApiResponse.createSuccessWithoutData(HttpStatus.OK.value(),
                    "비속어가 포함되어 있지 않습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<?>> filterLetter(FilterLetterDto.Req req) {
        String nickname = req.getLetter().getNickname();
        String content = req.getLetter().getContent();

        // 여기에 비속어 확인 로직 추가
        if (containsProfanity(nickname) || containsProfanity(content)) {
            ApiResponse<String> errorResponse = ApiResponse.createFailWithoutData(HttpStatus.BAD_REQUEST.value(),
                    "비속어가 포함되어 있습니다.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } else {
            ApiResponse<String> successResponse = ApiResponse.createSuccessWithoutData(HttpStatus.OK.value(),
                    "비속어가 포함되어 있지 않습니다.");
            return ResponseEntity.status(HttpStatus.OK).body(successResponse);
        }
    }

    // 비속어 확인 로직
    private boolean containsProfanity(String text) {
        // 여기에 실제 비속어 확인 로직을 추가
        return badWordFiltering.check(text);
    }
}
