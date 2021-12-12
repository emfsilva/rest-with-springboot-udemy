package io.github.emfsilva.restfull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Greeting {

    private final long id;
    private final String content;
}
