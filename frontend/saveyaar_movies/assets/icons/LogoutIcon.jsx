import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const LogoutIcon = (props) => (
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
      <Path d="M8.333 6.667V5.001A1.667 1.667 0 0 1 10 3.334h5.833A1.666 1.666 0 0 1 17.5 5.001v10a1.667 1.667 0 0 1-1.667 1.666H10a1.667 1.667 0 0 1-1.667-1.666v-1.667" />
      <Path d="M12.5 10h-10L5 7.5M5 12.5 2.5 10" />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M0 0h20v20H0z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { LogoutIcon }
