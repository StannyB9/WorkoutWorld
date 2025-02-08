package com.workoutapp.dto.language;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
public class LanguageVO {
    private Long id;
    private String code;
}
