SELECT
    cntrycode,
    COUNT(*) AS numcust,
    SUM(c_acctbal) AS totacctbal
FROM
    (
        SELECT
            SUBSTRING(c_phone FROM 1 FOR 2) AS cntrycode,
            c_acctbal
        FROM
            customer
        WHERE
                SUBSTRING(c_phone FROM 1 FOR 2) IN (?, ?, ?, ?, ?, ?, ?)
          AND c_acctbal >
              (
                  SELECT
                      AVG(c_acctbal)
                  FROM
                      customer
                  WHERE
                          c_acctbal > 0.00
                    AND SUBSTRING(c_phone FROM 1 FOR 2) IN (?, ?, ?, ?, ?, ?, ?)
              )
          AND NOT EXISTS
            (
            SELECT
                *
            FROM
                orders
            WHERE
                    o_custkey = c_custkey
            )
    )
        AS custsale
GROUP BY
    cntrycode
ORDER BY
    cntrycode
