import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const MySubscriptionsIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
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
      <Path d="M17.5 3.5v4.667a1.167 1.167 0 0 0 1.167 1.166h4.666" />
      <Path d="M21 19.833h-8.167A2.333 2.333 0 0 1 10.5 17.5V5.833A2.333 2.333 0 0 1 12.833 3.5H17.5l5.833 5.833V17.5A2.333 2.333 0 0 1 21 19.833Z" />
      <Path d="M18.667 19.835v2.333a2.333 2.333 0 0 1-2.334 2.333H8.168a2.333 2.333 0 0 1-2.333-2.333V10.501a2.333 2.333 0 0 1 2.333-2.333H10.5" />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M0 0h28v28H0z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { MySubscriptionsIcon }
