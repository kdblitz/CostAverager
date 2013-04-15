CostAverager
============

Simulates cost averaging on a 1 year period worth of historical data.

The basic data which is needed to simulate cost average is:
1.) Stock code (at the moment Philippine stock only are supported)
2.) Board lot
3.) Amount to invest per interval.
4.) Interval (in days) - how frequent you would be investing.

Limitations:
1.) This is only using 1 year worth of historical data.
2.) This does not yet take into account transaction costs.
3.) Interval parameter does not count non-business days (weekends/holidays?), so only rough estimation of gain/loss can be given by the program.
