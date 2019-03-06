package com.leyou.common.exception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class LyException extends RuntimeException {
    private int status;

    public LyException(ExceptionEnum em) {
        super(em.msg());
        this.status = em.status();
    }

    public LyException(ExceptionEnum em, Throwable cause) {
        super(em.msg(), cause);
        this.status = em.status();
    }
}
