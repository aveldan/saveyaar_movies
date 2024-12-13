import * as React from "react";
import Svg, { Circle, Defs, LinearGradient, Stop } from "react-native-svg";
const DotIconPurple = (props) => (
  <Svg
    width={6}
    height={7}
    viewBox="0 0 6 7"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <Circle
      cx={2.87805}
      cy={3.31555}
      r={2.87805}
      fill="url(#paint0_linear_4099_2776)"
    />
    <Defs>
      <LinearGradient
        id="paint0_linear_4099_2776"
        x1={2.87805}
        y1={0.4375}
        x2={2.87805}
        y2={6.1936}
        gradientUnits="userSpaceOnUse"
      >
        <Stop stopColor="#4361EE" />
        <Stop offset={1} stopColor="#8C3FFF" />
      </LinearGradient>
    </Defs>
  </Svg>
);

export { DotIconPurple };