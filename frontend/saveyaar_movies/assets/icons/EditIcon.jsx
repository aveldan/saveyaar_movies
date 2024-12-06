import * as React from "react"
import Svg, { G, Path, Defs, ClipPath } from "react-native-svg"
const EditIcon = (props) => (
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
      strokeWidth={1.5}
      clipPath="url(#a)"
    >
      <Path d="M7.887 7h-1a2 2 0 0 0-2 2v9a2 2 0 0 0 2 2h9a2 2 0 0 0 2-2v-1" />
      <Path d="M21.272 6.585a2.1 2.1 0 1 0-2.97-2.97L9.887 12v3h3l8.385-8.415ZM16.887 5l3 3" />
    </G>
    <Defs>
      <ClipPath id="a">
        <Path fill="#fff" d="M.887 0h24v24h-24z" />
      </ClipPath>
    </Defs>
  </Svg>
)
export { EditIcon }