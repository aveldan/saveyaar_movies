import React from "react";
import { StyleSheet, View } from "react-native";
import TabScreenWrapper from "../../components/TabScreenWrapper";
import { usePathname } from "expo-router";
import { HomeIcon } from '@/assets/svg'

export default function HomeScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/" || pathname == "/index";
	
	  
	return (
		<TabScreenWrapper isActive={isActive} slideDirection="right">
			<View style={styles.container}>
				<View style={styles.square} />
			</View>
		</TabScreenWrapper>
	);
};

const styles = StyleSheet.create({
	container: {
        flex: 1, // Occupy the entire screen
        justifyContent: "center", // Center vertically
        alignItems: "center", // Center horizontally
		// backgroundColor: "#000"
    },
    square: {
        width: 100, // Square's width
        height: 100, // Square's height
        backgroundColor: "blue", // Square's color
    }
});