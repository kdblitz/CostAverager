CostAverager
============

Simulates cost averaging on a 1 year period worth of historical data.

The basic data which is needed to simulate cost average is:
- Stock code (at the moment Philippine stock only are supported)
- Board lot
- Amount to invest per interval
- Interval (in days/weeks/months)* - how frequent you would be investing a fixed amount
 
*on the event of that interval falls on a holiday/weekend, the next nearest open market date would be used.

Limitations:
--------------

- This is only using 1 year worth of historical data.
- This does not yet take into account transaction costs.
