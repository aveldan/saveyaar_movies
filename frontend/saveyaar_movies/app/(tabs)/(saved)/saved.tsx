import React from "react";
import { StyleSheet, View } from "react-native";
import TabScreenWrapper from "../../../components/TabScreenWrapper";
import { usePathname } from "expo-router";
import HeaderComponent from "@/components/HeaderComponent";

export default function SavedScreen() {

	const pathname = usePathname();
	const isActive = pathname === "/saved";
	

	return (
		<TabScreenWrapper isActive={isActive} slideDirection='left'>
			<HeaderComponent title="Saved"/>
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
    },
    square: {
        width: 100, // Square's width
        height: 100, // Square's height
        backgroundColor: "green", // Square's color
    },
});