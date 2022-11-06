SELECT
    o_orderpriority,
    COUNT(*) AS order_count
FROM
    orders
WHERE
        o_orderdate >= DATE ?
  AND o_orderdate < DATE ? + INTERVAL '3' MONTH
  AND EXISTS
    (
    SELECT
        *
    FROM
        lineitem
    WHERE
            l_orderkey = o_orderkey
      AND l_commitdate < l_receiptdate
    )
GROUP BY
    o_orderpriority
ORDER BY
    o_orderpriority
