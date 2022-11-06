SELECT
    ps_partkey,
    SUM(ps_supplycost * ps_availqty) AS VALUE
FROM
    partsupp,
    supplier,
    nation
WHERE
    ps_suppkey = s_suppkey
  AND s_nationkey = n_nationkey
  AND n_name = ?
GROUP BY
    ps_partkey
HAVING
    SUM(ps_supplycost * ps_availqty) > (
    SELECT
    SUM(ps_supplycost * ps_availqty) * ?
    FROM
    partsupp, supplier, nation
    WHERE
    ps_suppkey = s_suppkey
   AND s_nationkey = n_nationkey
   AND n_name = ? )
ORDER BY
    VALUE DESC
