CostAverager
============

Simulates cost averaging on a 1 year period worth of historical data.

The basic data which is needed to simulate cost average is:
- Stock code (at the moment Philippine stock only are supported)
- Board lot
- Amount to invest per interval.
- Interval (in days) - how frequent you would be investing.

Limitations:
--------------

- This is only using 1 year worth of historical data.
- This does not yet take into account transaction costs.
- Interval parameter does not count non-business days (weekends/holidays?), so only rough estimation of gain/loss can be given by the program.
