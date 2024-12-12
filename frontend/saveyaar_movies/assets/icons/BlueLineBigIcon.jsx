import * as React from "react";
import Svg, { Path, Defs, LinearGradient, Stop } from "react-native-svg";

const BlueLineBigIcon = (props) => (
  <Svg
    width={107}
    height={4}
    viewBox="0 0 107 4"
    fill="none"
    xmlns="http://www.w3.org/2000/svg"
    {...props}
  >
    <Path
      d="M0 3.6037C0 1.61343 1.61343 0 3.6037 0H103.396C105.387 0 107 1.61343 107 3.6037H0Z"
      fill="url(#paint0_linear_4041_2835)"
    />
    <Defs>
      <LinearGradient
        id="paint0_linear_4041_2835"
        x1={53.5}
        y1={0}
        x2={53.5}
        y2={3.6037}
        gradientUnits="userSpaceOnUse"
      >
        <Stop stopColor="#4361EE" />
        <Stop offset={1} stopColor="#8C3FFF" />
      </LinearGradient>
    </Defs>
  </Svg>
);

export { BlueLineBigIcon };