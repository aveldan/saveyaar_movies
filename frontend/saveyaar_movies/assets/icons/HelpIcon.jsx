import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const HelpIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <G
      stroke="#9C7CDE"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={1.25}
      clipPath="url(#a)"
    >
      <Path d="M2.5 10a7.5 7.5 0 1 0 15 0 7.5 7.5 0 0 0-15 0ZM10 13.334v.008" />
      <Path d="M10 10.833a1.667 1.667 0 1 0-.314-3.3 1.65 1.65 0 0 0-.936.551" />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M0 0h20v20H0z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { HelpIcon }
