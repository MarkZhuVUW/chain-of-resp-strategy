package net.markz.checkout.api.chains;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.util.Set;

@Builder
@EqualsAndHashCode
@Data
public class StepChain<T, E> {

    @Singular
    private Set<AbstractStep<T, E>> steps;


    public Response<E> process(final Context<T> context) {
        final var response = new Response<E>();

        steps.forEach(rule -> rule.tryProcess(context, response));

        return response;
    }
}
