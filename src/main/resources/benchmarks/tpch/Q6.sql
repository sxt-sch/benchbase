SELECT
    SUM(l_extendedprice * l_discount) AS revenue
FROM
    lineitem
WHERE
        l_shipdate >= DATE ?
  AND l_shipdate < DATE ?
  AND l_discount BETWEEN ? - 0.01 AND ? + 0.01
  AND l_quantity < ?