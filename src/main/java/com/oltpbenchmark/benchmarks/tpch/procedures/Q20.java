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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q20 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(TPCHUtil.loadQuery("Q20.sql"));

    @Override
    protected PreparedStatement getStatement(Connection conn, RandomGenerator rand, double scaleFactor) throws SQLException {
        // COLOR is randomly selected within the list of values defined for the generation of P_NAME
        String color = "'" + TPCHUtil.choice(TPCHConstants.P_NAME_GENERATOR, rand) + "%" + "'";

        // DATE is the first of January of a randomly selected year within 1993..1997
        int year = rand.number(1993, 1997);
        String date1 = String.format("'%d-01-01'", year);
        String date2 = String.format("'%d-01-01'", year + 1);

        // NATION is randomly selected within the list of values defined for N_NAME in Clause 4.2.3
        String nation = TPCHUtil.choice(TPCHConstants.N_NAME, rand);
/*
        PreparedStatement stmt = this.getPreparedStatement(conn, query_stmt);
        stmt.setString(1, color);
        stmt.setDate(2, Date.valueOf(date1));
        stmt.setDate(3, Date.valueOf(date2));
        stmt.setString(4, nation);
*/
        String qstr = TPCHUtil.loadQuery("Q20.sql");
        qstr = qstr.replaceFirst("\\?", color);
        qstr = qstr.replaceFirst("\\?", date1);
        qstr = qstr.replaceFirst("\\?", date2);
        qstr = qstr.replaceFirst("\\?", "'" + nation + "'");

        SQLStmt qstmt = new SQLStmt(qstr);
        PreparedStatement stmt = this.getPreparedStatement(conn, qstmt);

        return stmt;
    }
}
