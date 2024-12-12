import * as React from "react";
import Svg, { Line } from "react-native-svg";
const WhiteLine = (props) => (
  <Svg
    width={390}
    height={2}
    viewBox="0 0 390 2"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <Line
      y1={1.10352}
      x2={390}
      y2={1.10352}
      stroke="white"
      strokeOpacity={0.08}
    />
  </Svg>
);

export { WhiteLine };