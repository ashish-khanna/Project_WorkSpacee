<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" indent="yes" />
	<xsl:template match="/">
		<stops>
			<xsl:for-each select="stop_list/direction/stop">
				<xsl:sort select="@stop_order" order="ascending" />
				<xsl:choose>
					<xsl:when test="../@direction_id= '0'">
						<stop>
							<routeid>
								<xsl:value-of select="../../@route_id" />
							</routeid>
							<station_name>
								<xsl:value-of select="@stop_name" />
							</station_name>
							<stopid>
								<xsl:value-of select="@stop_id"></xsl:value-of>
							</stopid>
							<stoporder>
								<xsl:value-of select="@stop_order"></xsl:value-of>
							</stoporder>
							<directionid>
								<xsl:value-of select="../@direction_id"></xsl:value-of>
							</directionid>
							<stoplat>
								<xsl:value-of select="@stop_lat"></xsl:value-of>
							</stoplat>
							<stoplon>
								<xsl:value-of select="@stop_lon"></xsl:value-of>
							</stoplon>
						</stop>
					</xsl:when>
				</xsl:choose>
			</xsl:for-each>
		</stops>

	</xsl:template>
</xsl:stylesheet>