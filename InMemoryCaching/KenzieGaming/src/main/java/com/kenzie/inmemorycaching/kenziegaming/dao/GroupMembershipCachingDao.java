package com.kenzie.inmemorycaching.kenziegaming.dao;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;

import javax.inject.Inject;
import java.util.List;

public class GroupMembershipCachingDao {
    // Create your cache here
    private final LoadingCache<String, List<String>> groupMembershipCache;
    private GroupMembershipDao groupMembershipDao;

    @Inject
    public GroupMembershipCachingDao(final GroupMembershipDao delegateDao) {
        // Initialize the cache
        this.groupMembershipCache= CacheBuilder.newBuilder()
                .build(CacheLoader.from(delegateDao::getGroupIdsForUser));
        this.groupMembershipDao = delegateDao;
    }

    // Implement your method here
    public List<String> isUserInGroup(GroupMembershipCacheKey key) {
        return groupMembershipCache.getUnchecked(key.getUserId());
    }

}
