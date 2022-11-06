CREATE view revenue0 (supplier_no, total_revenue) AS
SELECT
    l_suppkey,
    SUM(l_extendedprice * (1 - l_discount))
FROM
    lineitem
WHERE
        l_shipdate >= DATE ?
  AND l_shipdate < DATE ? + INTERVAL '3' MONTH
GROUP BY
    l_suppkey