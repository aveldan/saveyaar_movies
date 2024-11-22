import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const HomeIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props.width}
    height={props.height}
    fill="none"
    {...props}
  >
    <G
      stroke="#A6A6A6"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={1.75}
      clipPath="url(#a)"
    >
      <Path d="M6.333 14H4L14.5 3.5 25 14h-2.333M6.333 14v8.167A2.333 2.333 0 0 0 8.667 24.5h11.666a2.333 2.333 0 0 0 2.334-2.333V14" />
      <Path d="M12.167 14h4.666v4.667h-4.666V14Z" />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M.5 0h28v28H.5z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { HomeIcon }