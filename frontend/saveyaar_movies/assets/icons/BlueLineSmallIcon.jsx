import * as React from "react";
import Svg, { Path, Defs, LinearGradient, Stop } from "react-native-svg";
const BlueLineSmallIcon = (props) => (
  <Svg
    width={27}
    height={3}
    viewBox="0 0 27 3"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <Path
      d="M0.446045 0H26.446C26.446 1.65685 25.1029 3 23.446 3H3.44605C1.78919 3 0.446045 1.65685 0.446045 0Z"
      fill="url(#paint0_linear_4041_2761)"
    />
    <Defs>
      <LinearGradient
        id="paint0_linear_4041_2761"
        x1={13.446}
        y1={0}
        x2={13.446}
        y2={3}
        gradientUnits="userSpaceOnUse"
      >
        <Stop stopColor="#4361EE" />
        <Stop offset={1} stopColor="#8C3FFF" />
      </LinearGradient>
    </Defs>
  </Svg>
);

export { BlueLineSmallIcon };