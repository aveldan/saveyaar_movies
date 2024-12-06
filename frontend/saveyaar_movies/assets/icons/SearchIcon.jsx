import * as React from "react"
import Svg, { Path } from "react-native-svg"
const SearchIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Path
      stroke="#A6A6A6"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={2.333}
      d="m25 24.5-7-7M4 11.667a8.167 8.167 0 1 0 16.333 0 8.167 8.167 0 0 0-16.333 0Z"
    />
  </Svg>
)
export { SearchIcon }