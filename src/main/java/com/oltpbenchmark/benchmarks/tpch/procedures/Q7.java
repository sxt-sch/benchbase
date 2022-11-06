/*
 * Copyright 2020 by OLTPBenchmark Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.oltpbenchmark.benchmarks.tpch.procedures;

import com.oltpbenchmark.api.SQLStmt;
import com.oltpbenchmark.benchmarks.tpch.TPCHConstants;
import com.oltpbenchmark.benchmarks.tpch.TPCHUtil;
import com.oltpbenchmark.util.RandomGenerator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q7 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(TPCHUtil.loadQuery("Q7.sql"));

    @Override
    protected PreparedStatement getStatement(Connection conn, RandomGenerator rand, double scaleFactor) throws SQLException {
        // NATION1 is randomly selected within the list of values defined for N_NAME in Clause 4.2.3
        String nation1 = TPCHUtil.choice(TPCHConstants.N_NAME, rand);

        // NATION2 is randomly selected within the list of values defined for N_NAME in Clause 4.2.3
        // and must be different from the value selected for NATION1 in item 1 above
        String nation2 = nation1;
        while (nation2.equals(nation1)) {
            nation2 = TPCHUtil.choice(TPCHConstants.N_NAME, rand);
        }

        PreparedStatement stmt = this.getPreparedStatement(conn, query_stmt);
        stmt.setString(1, nation1);
        stmt.setString(2, nation2);
        stmt.setString(3, nation2);
        stmt.setString(4, nation1);
        return stmt;
    }
}
