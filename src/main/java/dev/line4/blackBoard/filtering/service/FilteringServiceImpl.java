package dev.line4.blackBoard.filtering.service;

import com.vane.badwordfiltering.BadWordFiltering;
import dev.line4.blackBoard.filtering.dto.FilterBlackBoardDto;
import dev.line4.blackBoard.utils.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FilteringServiceImpl implements FilteringService {

    // 비속어 확인
    @Override
    public ResponseEntity<ApiResponse<?>> filterBlackBoard(FilterBlackBoardDto dto) throws Exception {
        String title = dto.getBlackboard().getTitle();
        String introduction = dto.getBlackboard().getIntroduction();
        String userId = dto.getBlackboard().getUserId();

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

    // 비속어 확인 로직
    private boolean containsProfanity(String text) {
        // 여기에 실제 비속어 확인 로직을 추가
        BadWordFiltering badWordFiltering = new BadWordFiltering();
        return badWordFiltering.check(text);
    }
}
