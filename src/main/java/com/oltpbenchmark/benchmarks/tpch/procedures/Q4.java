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
import com.oltpbenchmark.benchmarks.tpch.TPCHUtil;
import com.oltpbenchmark.util.RandomGenerator;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q4 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(TPCHUtil.loadQuery("Q4.sql"));

    @Override
    protected PreparedStatement getStatement(Connection conn, RandomGenerator rand, double scaleFactor) throws SQLException {
        int year = rand.number(1993, 1997);
        int month = rand.number(1, 10);
        String date = String.format("%d-%02d-01", year, month);

        //PreparedStatement stmt = this.getPreparedStatement(conn, query_stmt);
        //stmt.setDate(1, Date.valueOf(date));
        //stmt.setDate(2, Date.valueOf(date));
        date = "'" + date + "'";
        String qstr = TPCHUtil.loadQuery("Q4.sql");
        qstr = qstr.replaceFirst("\\?", date);
        qstr = qstr.replaceFirst("\\?", date);

        SQLStmt qstmt = new SQLStmt(qstr);
        PreparedStatement stmt = this.getPreparedStatement(conn, qstmt);
        return stmt;
    }
}
