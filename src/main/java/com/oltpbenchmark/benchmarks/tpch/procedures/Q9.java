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

public class Q9 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(TPCHUtil.loadQuery("Q9.sql"));

    @Override
    protected PreparedStatement getStatement(Connection conn, RandomGenerator rand, double scaleFactor) throws SQLException {
        // COLOR is randomly selected within the list of values defined for the generation of P_NAME in Clause 4.2.3
        String color = "%" + TPCHUtil.choice(TPCHConstants.P_NAME_GENERATOR, rand) + "%";

        LOG.debug("attempting to execute sql [{}] for color [{}]", query_stmt.getSQL(), color);
/*
        PreparedStatement stmt = this.getPreparedStatement(conn, query_stmt);
        stmt.setString(1, color);
 */
        String q1str = TPCHUtil.loadQuery("Q9.sql");
        q1str = q1str.replace( "?", "'" + color + "'");
        SQLStmt qstmt = new SQLStmt(q1str);
        PreparedStatement stmt = this.getPreparedStatement(conn, qstmt);

        return stmt;
    }
}
