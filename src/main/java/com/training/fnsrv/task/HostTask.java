package com.training.fnsrv.task;

import com.training.fnsrv.model.Host;
import com.training.fnsrv.sshclient.SshClient;
import lombok.extern.java.Log;

import java.util.Iterator;

@Log
public class HostTask extends Task {
    private SshClient ssh;

    public HostTask(Host host) {
        setId(TaskExecutor.genNewTaskId());

        ssh = new SshClient(host.getAddr(), host.getUser(), host.getPassword());

        addNextTask(new IpInterfaceTask(1L));
        addNextTask(new IpRouteTask(2L));
    }

    @Override
    public void run() {
        ssh.connect();

        Iterator<Task> iter = iterator();
        while (iter.hasNext()) {
            Task task = iter.next();

            ssh.exec(task.getCmd(), task);
            task.setStatus(TaskStatus.DONE);
            log.info(String.format("RUN task with parentid='%d'; id='%d'", task.getParentId(), task.getId()));
        }
        ssh.disconnect();
    }
}
