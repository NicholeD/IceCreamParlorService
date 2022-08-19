package com.kenzie.inmemorycaching.kenziegaming.dao;

import com.kenzie.inmemorycaching.kenziegaming.dao.models.GroupMembershipCacheKey;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GroupMembershipCachingDaoTest {
    @Mock
    private GroupMembershipDao membershipDao;

    // The unit under test
    @InjectMocks
    private GroupMembershipCachingDao cachingMembershipDao;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    // Rename this method
    @Test
    public void isUserInGroup_returnsTrue() {
        // Implement your test here
        // GIVEN
        List<String> groupIdsForUser = new ArrayList<>();
        groupIdsForUser.add("groupWhatIHadForBreakfast");
        groupIdsForUser.add("groupThingsMyCatTellsMe");
        groupIdsForUser.add("groupABC");

        GroupMembershipCacheKey cacheKey = new GroupMembershipCacheKey("user123", "groupABC");

        when(membershipDao.getGroupIdsForUser(any())).thenReturn(groupIdsForUser);

        // WHEN

        cachingMembershipDao = new GroupMembershipCachingDao(membershipDao);
        boolean inGroup = cachingMembershipDao.isUserInGroup(cacheKey).contains(cacheKey.getGroupId());

        // THEN
        verify(membershipDao, times(1)).getGroupIdsForUser(cacheKey.getUserId());
        assertTrue(inGroup);
    }

    // Rename this method
    @Test
    public void groupMembershipCaching_notNull() {
        // Implement your test here
        // GIVEN
        List<String> groupIdsForUser = new ArrayList<>();
        groupIdsForUser.add("groupWhatIHadForBreakfast");
        groupIdsForUser.add("groupThingsMyCatTellsMe");

        when(membershipDao.getGroupIdsForUser(any())).thenReturn(groupIdsForUser);

        // WHEN
        cachingMembershipDao = new GroupMembershipCachingDao(membershipDao);

        // THEN
        assertNotNull(cachingMembershipDao);
    }
}
