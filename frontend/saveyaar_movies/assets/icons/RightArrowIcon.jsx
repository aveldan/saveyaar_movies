import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const RightArrow = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <G clipPath="url(#a)">
      <Path
        stroke="#9C7CDE"
        strokeLinecap="round"
        strokeLinejoin="round"
        strokeWidth={1.25}
        d="m7.5 5 5 5-5 5"
      />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M0 0h20v20H0z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { RightArrow }
