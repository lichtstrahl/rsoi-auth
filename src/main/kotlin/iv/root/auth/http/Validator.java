package iv.root.auth.http;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class Validator {
    private static final String VALIDATOR_NOT_READY = "Validator not ready";

    private List<String> messages = new LinkedList<>();
    private boolean ready = false;

    public Validator notNullJSON(Object field, String name) {
        if (!ready) throw new IllegalArgumentException(VALIDATOR_NOT_READY);
        if (field == null)
            messages.add(String.format("JSON field %s must be not null", name));
        return this;
    }

    public Validator notNullParam(Object param, String name) {
        if (!ready) throw new IllegalArgumentException(VALIDATOR_NOT_READY);
        if (param == null)
            messages.add(String.format("Request param %s must be not null", name));
        return this;
    }

    public Validator reset() {
        messages.clear();
        begin();
        return this;
    }

    public Validator begin() {
        ready = true;
        return this;
    }

    public Validator end() {
        ready = false;
        return this;
    }

    public Validator load(StringBuilder buffer) {
        for (String str : messages)
            buffer.append(str.concat("; "));
        return this;
    }

    public StringBuilder export() {
        StringBuilder buffer = new StringBuilder();

        load(buffer);
        end();
        return buffer;
    }
}
