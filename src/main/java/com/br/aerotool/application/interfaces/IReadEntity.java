package com.br.aerotool.application.interfaces;

import java.util.Optional;

public interface IReadEntity<T, K> {
    Optional<T> findById(K id);
}
