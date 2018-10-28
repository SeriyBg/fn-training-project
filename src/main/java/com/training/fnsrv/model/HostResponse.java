package com.training.fnsrv.model;

import com.training.fnsrv.task.TaskStatus;
import lombok.Data;

@Data
public class HostResponse {
    private Long id;
    private TaskStatus status;
}
