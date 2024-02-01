package com.ssafy.relpl.dto.response;


import lombok.*;

import java.util.List;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingDataDto {
    private List<RankingEntry> dailyRanking;
    private List<RankingEntry> weeklyRanking;
    private List<RankingEntry> monthlyRanking;
}
