package com.saike.ucm.dao;

import com.saike.ucm.domain.IdbInformation;
import com.saike.ucm.domain.dao.Pagination;

import java.util.List;

/**
 * Created by huawei on 12/28/15.
 */
public interface IdbDAO {

    List<IdbInformation> paginateDatabase(Pagination<IdbInformation> condition, int start, int length);

    int countDatabase(Pagination<IdbInformation> condition);

}
