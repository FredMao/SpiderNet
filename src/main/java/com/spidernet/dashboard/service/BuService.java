package com.spidernet.dashboard.service;

import java.util.List;

import com.spidernet.dashboard.entity.Bu;

/**
 * 
 * @author nick
 *
 */
public interface BuService
{
    Bu findBuName(String buId);
    List<Bu> queryBu();
    List<Bu> queryBus(Bu bu);
    int addBu(Bu bu);
}
