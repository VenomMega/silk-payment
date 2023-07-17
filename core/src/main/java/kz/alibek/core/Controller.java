package kz.alibek.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface Controller<E, ID> {

    ResponseEntity<E> findById(@PathVariable ID id);
    ResponseEntity<E> updateById(@RequestBody E entity, @PathVariable ID id);

    ResponseEntity<Void> delete(@PathVariable ID id);
}
