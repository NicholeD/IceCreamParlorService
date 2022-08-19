package com.kenzie.inmemorycaching.kenziegaming.dao.models;

import java.util.Objects;

public final class GroupMembershipCacheKey  {

    // Implement your cache key class here
    private final String userId;
    private final String groupId;

    public GroupMembershipCacheKey(final String userId, final String groupId) {
        this.userId = userId;
        this.groupId = groupId;
    }

    public String getUserId() {
        return userId;
    }


    public String getGroupId() {
        return groupId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMembershipCacheKey that = (GroupMembershipCacheKey) o;
        return userId.equals(that.userId) && groupId.equals(that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }
}
