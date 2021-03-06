package internet_store.core.response.client;

import internet_store.core.core_error.CoreError;
import internet_store.core.core_error.CoreErrorResponse;
import lombok.Getter;

import java.util.List;

public class DeleteClientResponse extends CoreErrorResponse {
    @Getter
    private long id;

    public DeleteClientResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteClientResponse(long id) {
        this.id = id;
    }
}
