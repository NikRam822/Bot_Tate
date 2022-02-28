package com.company.base;

import java.io.Serializable;

/**
 * Класс для созданя объекта (приглашение в группу)
 * Поля: отправитель, ссылка на группу.
 */
public class Invite implements Serializable {
    private static final long serialVersionUID = 2529685098267757690L;
    private com.company.base.User sender;
    private com.company.base.Group inviteGroup;

    public Invite(com.company.base.User user, com.company.base.Group group) {
        sender = user;
        inviteGroup = group;
    }

    public com.company.base.Group getInviteGroup() {
        return inviteGroup;
    }

    public com.company.base.User getSender() {
        return sender;
    }
}
