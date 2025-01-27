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
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Q1 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(TPCHUtil.loadQuery("Q1.sql"));

    @Override
    protected PreparedStatement getStatement(Connection conn, RandomGenerator rand, double scaleFactor) throws SQLException {
        int deltaNum = rand.number(60, 120);
        String delta = String.valueOf(deltaNum);

        // TODO: could fix in Ignite JDBC driver
        // PreparedStatement stmt = this.getPreparedStatement(conn, query_stmt);
        // stmt.setInt(1, deltaNum);

        String q1str = TPCHUtil.loadQuery("Q1.sql");
        q1str = q1str.replace( "?", delta);
        SQLStmt qstmt = new SQLStmt(q1str);
        PreparedStatement stmt = this.getPreparedStatement(conn, qstmt);

        return stmt;
    }
}
