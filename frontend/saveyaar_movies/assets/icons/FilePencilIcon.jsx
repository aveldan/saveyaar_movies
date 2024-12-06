import * as React from "react"
import Svg, { Path } from "react-native-svg"
const FilePensilIcon = (props) => (
  <Svg
    xmlns="http://www.w3.org/2000/svg"
    width={props?.width || 24}
    height={props?.height || 24}
    fill="none"
    {...props}
  >
    <Path
      stroke="#9C7CDE"
      strokeLinecap="round"
      strokeLinejoin="round"
      strokeWidth={1.667}
      d="M11.667 2.5v3.333a.833.833 0 0 0 .833.834h3.333M11.667 2.5H5.833a1.667 1.667 0 0 0-1.666 1.667v11.666A1.666 1.666 0 0 0 5.833 17.5h8.334a1.667 1.667 0 0 0 1.666-1.667V6.667M11.667 2.5l4.166 4.167M8.333 15l4.167-4.166a1.179 1.179 0 0 0-1.667-1.667l-4.166 4.167V15h1.666Z"
    />
  </Svg>
)
export { FilePensilIcon }
