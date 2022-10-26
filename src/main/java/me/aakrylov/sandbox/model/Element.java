package me.aakrylov.sandbox.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Element {

    private String stringField;
    private Integer intField;
    private LocalDateTime ldtField;
    private List<String> stringList;

}
