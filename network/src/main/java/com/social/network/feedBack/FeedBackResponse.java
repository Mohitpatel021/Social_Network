package com.social.network.feedBack;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedBackResponse {
    private Double note;
    private String comment;
    private boolean ownFeedback;

}